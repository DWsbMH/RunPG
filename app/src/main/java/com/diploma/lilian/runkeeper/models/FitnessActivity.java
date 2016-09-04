package com.diploma.lilian.runkeeper.models;

import java.util.Arrays;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class FitnessActivity {
	public static class Distance {
		private Double timestamp; //The number of seconds since the start of the activity
		private Double distance; //The total distance traveled since the start of the activity, in meters

		public Double getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Double timestamp) {
			this.timestamp = timestamp;
		}
		public Double getDistance() {
			return distance;
		}
		public void setDistance(Double distance) {
			this.distance = distance;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((distance == null) ? 0 : distance.hashCode());
			result = prime * result
					+ ((timestamp == null) ? 0 : timestamp.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Distance other = (Distance) obj;
			if (distance == null) {
				if (other.distance != null)
					return false;
			} else if (!distance.equals(other.distance))
				return false;
			if (timestamp == null) {
				if (other.timestamp != null)
					return false;
			} else if (!timestamp.equals(other.timestamp))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Distance{" +
					"timestamp=" + timestamp +
					", distance=" + distance +
					'}';
		}
	}
	public static class Calories {
		private Double timestamp; //The number of seconds since the start of the activity
		private Double calories; //The total calories burned since the start of the activity

		public Double getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Double timestamp) {
			this.timestamp = timestamp;
		}
		public Double getCalories() {
			return calories;
		}
		public void setCalories(Double calories) {
			this.calories = calories;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((calories == null) ? 0 : calories.hashCode());
			result = prime * result
					+ ((timestamp == null) ? 0 : timestamp.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Calories other = (Calories) obj;
			if (calories == null) {
				if (other.calories != null)
					return false;
			} else if (!calories.equals(other.calories))
				return false;
			if (timestamp == null) {
				if (other.timestamp != null)
					return false;
			} else if (!timestamp.equals(other.timestamp))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Calories{" +
					"timestamp=" + timestamp +
					", calories=" + calories +
					'}';
		}
	}
	public static class Image {
		private Double timestamp; //The number of seconds since the start of the activity
		private Double latitude; //The latitude, in degrees (values increase northward and decrease southward)
		private Double longitude; //The longitude, in degrees (values increase eastward and decrease westward)
		private String uri; //The URI of the image
		private String thumbnail_uri; //The URI of the thumbnail version of the image

		public Double getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Double timestamp) {
			this.timestamp = timestamp;
		}
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public String getThumbnail_uri() {
			return thumbnail_uri;
		}
		public void setThumbnail_uri(String thumbnail_uri) {
			this.thumbnail_uri = thumbnail_uri;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((latitude == null) ? 0 : latitude.hashCode());
			result = prime * result
					+ ((longitude == null) ? 0 : longitude.hashCode());
			result = prime * result
					+ ((thumbnail_uri == null) ? 0 : thumbnail_uri.hashCode());
			result = prime * result
					+ ((timestamp == null) ? 0 : timestamp.hashCode());
			result = prime * result + ((uri == null) ? 0 : uri.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Image other = (Image) obj;
			if (latitude == null) {
				if (other.latitude != null)
					return false;
			} else if (!latitude.equals(other.latitude))
				return false;
			if (longitude == null) {
				if (other.longitude != null)
					return false;
			} else if (!longitude.equals(other.longitude))
				return false;
			if (thumbnail_uri == null) {
				if (other.thumbnail_uri != null)
					return false;
			} else if (!thumbnail_uri.equals(other.thumbnail_uri))
				return false;
			if (timestamp == null) {
				if (other.timestamp != null)
					return false;
			} else if (!timestamp.equals(other.timestamp))
				return false;
			if (uri == null) {
				if (other.uri != null)
					return false;
			} else if (!uri.equals(other.uri))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Image{" +
					"timestamp=" + timestamp +
					", latitude=" + latitude +
					", longitude=" + longitude +
					", uri='" + uri + '\'' +
					", thumbnail_uri='" + thumbnail_uri + '\'' +
					'}';
		}
	}
	
	private String uri; //The URI for this activity
	private Integer userID; //The unique ID of the user for the activity
	private String type; //The type of activity, as one of the following values: "Running", "Cycling", "Mountain Biking", "Walking", "Hiking", "Downhill Skiing", "Cross-Country Skiing", "Snowboarding", "Skating", "Swimming", "Wheelchair", "Rowing", "Elliptical", "Other"
	private String secondary_type; //The type of activity, as one of the following values: "Running", "Cycling", "Mountain Biking", "Walking", "Hiking", "Downhill Skiing", "Cross-Country Skiing", "Snowboarding", "Skating", "Swimming", "Wheelchair", "Rowing", "Elliptical", "Other"
	private String equipment; //The equipment used to complete this activity, as one of the following values: None, Treadmill, Stationary Bike, Elliptical, Row Machine. (Optional; if not specified, None is assumed.)
	private String start_time; //The starting time for the activity (e.g., "Sat, 1 Jan 2011 00:00:00")
	private Integer utc_offset;
	private Double total_distance; //The total distance traveled, in meters
	private Distance[] distance; //The sequence of time-stamped distance measurements (empty if not available)
	private Double duration; //The duration of the activity, in seconds
	private Integer average_heart_rate; //The user's average heart rate, in beats per minute (omitted if not available)
	private HeartRate[] heart_rate; //The sequence of time-stamped heart rate measurements (empty if not available)
	private Double total_calories; //The total calories burned
	private Calories[] calories; //The sequence of time-stamped caloric burn measurements (empty if not available)
	private Double climb; //The total elevation climbed over the course of the activity, in meters
	private String notes; //Any notes that the user has associated with the activity
	private Boolean is_live;
	private WGS84[] path; //The sequence of geographical points along the route (empty for stationary activities)
	private Image[] images; //The sequence of images along the route (empty if not available)
	private String share; //The name of the application that last modified this activity
	private String share_map;
	private String source; //The name of the application that last modified this activity
	private String entry_mode;
	private String tracking_mode; //The name of the application that last modified this activity
	private String activity; //The URL of the user's public, human-readable page for this activity
	private String comments; //The name of the application that last modified this activity
	private String previous; //The URI of the previous activity in the user's fitness feed (omitted for the oldest activity)
	private String next; //The URI of the next activity in the user's fitness feed (omitted for the most-recent activity)
	private String[] nearest_teammate_fitness_activities; //The URIs of the fitness activities closest in time to this activity for each street teammate (empty if no fitness activities have been recorded)
	private String nearest_strength_training_activity; //The URI of the strength training activity closest in time to this activity for the user (omitted if no strength training activities have been recorded)
	private String[] nearest_teammate_strength_training_activities; //The URIs of the strength training activities closest in time to this activity for each street teammate (empty if no strength training activities have been recorded)
	private String nearest_background_activity; //The URI of the background activity closest in time to this activity for the user (omitted if no background activities have been recorded)
	private String[] nearest_teammate_background_activities; //The URIs of the background activities closest in time to this activity for each street teammate (empty if no background activities have been recorded)
	private String nearest_sleep; //The URI of the sleep measurements closest in time to this activity for the user (omitted if no sleep measurements have been taken)
	private String[] nearest_teammate_sleep; //The URIs of the sleep measurements closest in time to this activity for each street teammate (empty if no sleep measurements have been taken)
	private String nearest_nutrition; //The URI of the nutrition measurement closest in time to this activity for the user (omitted if no nutrition measurements have been token)
	private String[] nearest_teammate_nutrition; //The URIs of the nutrition measurement closest in time to this activity for each street teammate (empty if no nutrition measurements have been token)
	private String  nearest_weight; //The URI of the weight measurement closest in time to this activity for the user (omitted if no weight measurements have been token)
	private String[] nearest_teammate_weight; //The URIs of the weight measurements closest in time to this activity for each street teammate (empty if no weight measurements have been token)
	private String nearest_general_measurement; //The URI of the general measurement measurement closest in time to this activity for the user (omitted if no general measurements have been token)
	private String[] nearest_teammate_general_measurements; //The URIs of the general measurements closest in time to this activity for each street teammate (empty if no general measurements have been token)
	private String nearest_diabetes; //The URI of the diabetes measurement closest in time to this activity for the user (omitted if no diabetes measurements have been token)
	private String[] nearest_teammate_diabetes; //The URIs of the diabetes measurements closest in time to this activity for each street teammate (empty if no diabetes measurements have been token)

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSecondary_type() {
		return secondary_type;
	}
	public void setSecondary_type(String secondary_type) {
		this.secondary_type = secondary_type;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public Integer getUtc_offset() {
		return utc_offset;
	}
	public void setUtc_offset(Integer utc_offset) {
		this.utc_offset = utc_offset;
	}
	public Double getTotal_distance() {
		return total_distance;
	}
	public void setTotal_distance(Double total_distance) {
		this.total_distance = total_distance;
	}
	public Distance[] getDistance() {
		return distance;
	}
	public void setDistance(Distance[] distance) {
		this.distance = distance;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public Integer getAverage_heart_rate() {
		return average_heart_rate;
	}
	public void setAverage_heart_rate(Integer average_heart_rate) {
		this.average_heart_rate = average_heart_rate;
	}
	public HeartRate[] getHeart_rate() {
		return heart_rate;
	}
	public void setHeart_rate(HeartRate[] heart_rate) {
		this.heart_rate = heart_rate;
	}
	public Double getTotal_calories() {
		return total_calories;
	}
	public void setTotal_calories(Double total_calories) {
		this.total_calories = total_calories;
	}
	public Calories[] getCalories() {
		return calories;
	}
	public void setCalories(Calories[] calories) {
		this.calories = calories;
	}
	public Double getClimb() {
		return climb;
	}
	public void setClimb(Double climb) {
		this.climb = climb;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Boolean getIs_live() {
		return is_live;
	}
	public void setIs_live(Boolean is_live) {
		this.is_live = is_live;
	}
	public WGS84[] getPath() {
		return path;
	}
	public void setPath(WGS84[] path) {
		this.path = path;
	}
	public Image[] getImages() {
		return images;
	}
	public void setImages(Image[] images) {
		this.images = images;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	public String getShare_map() {
		return share_map;
	}
	public void setShare_map(String share_map) {
		this.share_map = share_map;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getEntry_mode() {
		return entry_mode;
	}
	public void setEntry_mode(String entry_mode) {
		this.entry_mode = entry_mode;
	}
	public String getTracking_mode() {
		return tracking_mode;
	}
	public void setTracking_mode(String tracking_mode) {
		this.tracking_mode = tracking_mode;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String[] getNearest_teammate_fitness_activities() {
		return nearest_teammate_fitness_activities;
	}
	public void setNearest_teammate_fitness_activities(
			String[] nearest_teammate_fitness_activities) {
		this.nearest_teammate_fitness_activities = nearest_teammate_fitness_activities;
	}
	public String getNearest_strength_training_activity() {
		return nearest_strength_training_activity;
	}
	public void setNearest_strength_training_activity(
			String nearest_strength_training_activity) {
		this.nearest_strength_training_activity = nearest_strength_training_activity;
	}
	public String[] getNearest_teammate_strength_training_activities() {
		return nearest_teammate_strength_training_activities;
	}
	public void setNearest_teammate_strength_training_activities(
			String[] nearest_teammate_strength_training_activities) {
		this.nearest_teammate_strength_training_activities = nearest_teammate_strength_training_activities;
	}
	public String getNearest_background_activity() {
		return nearest_background_activity;
	}
	public void setNearest_background_activity(String nearest_background_activity) {
		this.nearest_background_activity = nearest_background_activity;
	}
	public String[] getNearest_teammate_background_activities() {
		return nearest_teammate_background_activities;
	}
	public void setNearest_teammate_background_activities(
			String[] nearest_teammate_background_activities) {
		this.nearest_teammate_background_activities = nearest_teammate_background_activities;
	}
	public String getNearest_sleep() {
		return nearest_sleep;
	}
	public void setNearest_sleep(String nearest_sleep) {
		this.nearest_sleep = nearest_sleep;
	}
	public String[] getNearest_teammate_sleep() {
		return nearest_teammate_sleep;
	}
	public void setNearest_teammate_sleep(String[] nearest_teammate_sleep) {
		this.nearest_teammate_sleep = nearest_teammate_sleep;
	}
	public String getNearest_nutrition() {
		return nearest_nutrition;
	}
	public void setNearest_nutrition(String nearest_nutrition) {
		this.nearest_nutrition = nearest_nutrition;
	}
	public String[] getNearest_teammate_nutrition() {
		return nearest_teammate_nutrition;
	}
	public void setNearest_teammate_nutrition(String[] nearest_teammate_nutrition) {
		this.nearest_teammate_nutrition = nearest_teammate_nutrition;
	}
	public String getNearest_weight() {
		return nearest_weight;
	}
	public void setNearest_weight(String nearest_weight) {
		this.nearest_weight = nearest_weight;
	}
	public String[] getNearest_teammate_weight() {
		return nearest_teammate_weight;
	}
	public void setNearest_teammate_weight(String[] nearest_teammate_weight) {
		this.nearest_teammate_weight = nearest_teammate_weight;
	}
	public String getNearest_general_measurement() {
		return nearest_general_measurement;
	}
	public void setNearest_general_measurement(String nearest_general_measurement) {
		this.nearest_general_measurement = nearest_general_measurement;
	}
	public String[] getNearest_teammate_general_measurements() {
		return nearest_teammate_general_measurements;
	}
	public void setNearest_teammate_general_measurements(
			String[] nearest_teammate_general_measurements) {
		this.nearest_teammate_general_measurements = nearest_teammate_general_measurements;
	}
	public String getNearest_diabetes() {
		return nearest_diabetes;
	}
	public void setNearest_diabetes(String nearest_diabetes) {
		this.nearest_diabetes = nearest_diabetes;
	}
	public String[] getNearest_teammate_diabetes() {
		return nearest_teammate_diabetes;
	}
	public void setNearest_teammate_diabetes(String[] nearest_teammate_diabetes) {
		this.nearest_teammate_diabetes = nearest_teammate_diabetes;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FitnessActivity)) return false;

		FitnessActivity that = (FitnessActivity) o;

		if (!uri.equals(that.uri)) return false;
		if (!userID.equals(that.userID)) return false;
		if (!type.equals(that.type)) return false;
		if (secondary_type != null ? !secondary_type.equals(that.secondary_type) : that.secondary_type != null)
			return false;
		if (equipment != null ? !equipment.equals(that.equipment) : that.equipment != null) return false;
		if (!start_time.equals(that.start_time)) return false;
		if (!utc_offset.equals(that.utc_offset)) return false;
		if (!total_distance.equals(that.total_distance)) return false;
		if (!duration.equals(that.duration)) return false;
		if (average_heart_rate != null ? !average_heart_rate.equals(that.average_heart_rate) : that.average_heart_rate != null)
			return false;
		if (total_calories != null ? !total_calories.equals(that.total_calories) : that.total_calories != null)
			return false;
		if (!climb.equals(that.climb)) return false;
		if (!is_live.equals(that.is_live)) return false;
		if (!share.equals(that.share)) return false;
		if (share_map != null ? !share_map.equals(that.share_map) : that.share_map != null) return false;
		if (!source.equals(that.source)) return false;
		if (!entry_mode.equals(that.entry_mode)) return false;
		if (!tracking_mode.equals(that.tracking_mode)) return false;
		if (!activity.equals(that.activity)) return false;
		if (!comments.equals(that.comments)) return false;
		if (!previous.equals(that.previous)) return false;
		return next.equals(that.next);

	}
	@Override
	public int hashCode() {
		int result = uri.hashCode();
		result = 31 * result + userID.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + (secondary_type != null ? secondary_type.hashCode() : 0);
		result = 31 * result + (equipment != null ? equipment.hashCode() : 0);
		result = 31 * result + start_time.hashCode();
		result = 31 * result + utc_offset.hashCode();
		result = 31 * result + total_distance.hashCode();
		result = 31 * result + Arrays.hashCode(distance);
		result = 31 * result + duration.hashCode();
		result = 31 * result + (average_heart_rate != null ? average_heart_rate.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(heart_rate);
		result = 31 * result + (total_calories != null ? total_calories.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(calories);
		result = 31 * result + climb.hashCode();
		result = 31 * result + (notes != null ? notes.hashCode() : 0);
		result = 31 * result + is_live.hashCode();
		result = 31 * result + Arrays.hashCode(path);
		result = 31 * result + Arrays.hashCode(images);
		result = 31 * result + share.hashCode();
		result = 31 * result + (share_map != null ? share_map.hashCode() : 0);
		result = 31 * result + source.hashCode();
		result = 31 * result + entry_mode.hashCode();
		result = 31 * result + tracking_mode.hashCode();
		result = 31 * result + activity.hashCode();
		result = 31 * result + comments.hashCode();
		result = 31 * result + previous.hashCode();
		result = 31 * result + next.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return "FitnessActivity{" +
				"uri='" + uri + '\'' +
				", userID=" + userID +
				", type='" + type + '\'' +
				", secondary_type='" + secondary_type + '\'' +
				", equipment='" + equipment + '\'' +
				", start_time='" + start_time + '\'' +
				", utc_offset=" + utc_offset +
				", total_distance=" + total_distance +
				", distance=" + Arrays.toString(distance) +
				", duration=" + duration +
				", average_heart_rate=" + average_heart_rate +
				", heart_rate=" + Arrays.toString(heart_rate) +
				", total_calories=" + total_calories +
				", calories=" + Arrays.toString(calories) +
				", climb=" + climb +
				", notes='" + notes + '\'' +
				", is_live=" + is_live +
				", path=" + Arrays.toString(path) +
				", images=" + Arrays.toString(images) +
				", share='" + share + '\'' +
				", share_map='" + share_map + '\'' +
				", source='" + source + '\'' +
				", entry_mode='" + entry_mode + '\'' +
				", tracking_mode='" + tracking_mode + '\'' +
				", activity='" + activity + '\'' +
				", comments='" + comments + '\'' +
				", previous='" + previous + '\'' +
				", next='" + next + '\'' +
				", nearest_teammate_fitness_activities=" + Arrays.toString(nearest_teammate_fitness_activities) +
				", nearest_strength_training_activity='" + nearest_strength_training_activity + '\'' +
				", nearest_teammate_strength_training_activities=" + Arrays.toString(nearest_teammate_strength_training_activities) +
				", nearest_background_activity='" + nearest_background_activity + '\'' +
				", nearest_teammate_background_activities=" + Arrays.toString(nearest_teammate_background_activities) +
				", nearest_sleep='" + nearest_sleep + '\'' +
				", nearest_teammate_sleep=" + Arrays.toString(nearest_teammate_sleep) +
				", nearest_nutrition='" + nearest_nutrition + '\'' +
				", nearest_teammate_nutrition=" + Arrays.toString(nearest_teammate_nutrition) +
				", nearest_weight='" + nearest_weight + '\'' +
				", nearest_teammate_weight=" + Arrays.toString(nearest_teammate_weight) +
				", nearest_general_measurement='" + nearest_general_measurement + '\'' +
				", nearest_teammate_general_measurements=" + Arrays.toString(nearest_teammate_general_measurements) +
				", nearest_diabetes='" + nearest_diabetes + '\'' +
				", nearest_teammate_diabetes=" + Arrays.toString(nearest_teammate_diabetes) +
				'}';
	}
}
