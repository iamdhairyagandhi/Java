package tubeVideosManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A video has a title, url, durationInMinutes and a genre. Comments about the
 * video are kept in an ArrayList (comments). For YouTube videos the url is the
 * one generated by the "Copy embed code" option.
 * 
 * @author UMCP CS Department
 *
 */
public class Video implements Comparable<Video> {
	private String title, url;
	private int durationInMinutes;
	private Genre videoGenre;
	private ArrayList<String> comments;

	/**
	 * Initializes a video object. If any parameter is null or if a string parameter
	 * is a blank (according to String class isBlank() method), the method will
	 * throw an IllegalArgumentException (with any message) and perform no
	 * processing. Also the same exception will be thrown if the duration is zero or
	 * negative.
	 * 
	 * @param title
	 * @param url
	 * @param durationInMinutes
	 * @param videoGenre
	 */
	public Video(String title, String url, int durationInMinutes, Genre videoGenre) {
		if (title.isBlank() || title == null || url == null || url.isBlank() || videoGenre == null
				|| durationInMinutes == 0) {
			throw new IllegalArgumentException("Invalid argument");
		} else {
			this.title = title;
			this.url = url;
			this.durationInMinutes = durationInMinutes;
			this.videoGenre = videoGenre;
			this.comments = new ArrayList<String>();

		}
	}

	/**
	 * Initializes the Video object so changes to the parameter do not affect the
	 * current object. Your implementation must be efficient (avoid any unnecessary
	 * copies).
	 * 
	 * @param video
	 */
	public Video(Video video) {
		this(video.title, video.url, video.durationInMinutes, video.videoGenre);
		for (int i = 0; i < video.comments.size(); i++) {
			this.comments.add(video.comments.get(i));
		}
	}

	/**
	 * Get method for title
	 * 
	 * @return title string
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get method for url
	 * 
	 * @return url string
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Get method for duration
	 * 
	 * @return duration
	 */
	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	/**
	 * Get method for video genre
	 * 
	 * @return string with genre
	 */
	public Genre getGenre() {
		return videoGenre;
	}

	/**
	 * Provided; please don't modify. toString for class
	 * 
	 * @return string with object info
	 */
	public String toString() {
		String answer = "Title: " + "\"" + title + "\"\n";

		answer += "Url: " + url + "\n";
		answer += "Duration (minutes): " + durationInMinutes + "\n";
		answer += "Genre: " + videoGenre + "\n";

		return answer;
	}

	/**
	 * Adds specified comments to the video. If the parameter is null or is a blank
	 * string (according to String class isBlank() method) the method will throw an
	 * IllegalArgumentException (with any message) and perform no processing.
	 * 
	 * @param comments
	 * @return true if comments added; false otherwise
	 */
	public boolean addComments(String comments) {
		boolean flag = false;
		if (comments == null || comments.isEmpty()) {

			throw new IllegalArgumentException("Invalid Argument");

		} else {
			this.comments.add(comments);
			flag = true;
		}
		return flag;
	}

	/**
	 * Returns copy so changes to the copy does not affect the original. Your
	 * implementation must be efficient (avoid any unnecessary copies).
	 * 
	 * @return ArrayList of strings
	 */
	public ArrayList<String> getComments() {
		return new ArrayList<String>(this.comments);
	}

	/**
	 * Videos will be compared using title. If we were to sort an ArrayList of
	 * Videos, they will appear in lexicographical (alphabetical) order (e.g, "A",
	 * "B", "C").
	 * 
	 * @return negative, 0, or positive value
	 */
	public int compareTo(Video video) {
		return this.title.compareTo(video.title);
	}

	/**
	 * Two Video objects are considered equal if they have the same title. Implement
	 * the method using the instanceof operator rather than using getClass().
	 * 
	 * @return true if objects are considered equal; false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		Video video = (Video) obj;
		if ((obj instanceof Video)) {
			if (this.title.equals(video.title)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}
}
