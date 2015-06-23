package com.itunes.parameter;

public enum ItunesMedia {

	MOVIE("movie"), PODCAST("podcast"), MUSIC("music"), MUSIC_VIDEO("musicVideo"), AUDIO_BOOK("audiobook"),
	SHORT_FILM("shortFilm"), TV_SHOW("tvShow"), SOFTWARE("software"), EBOOK("ebook"), ALL("all");
		
	public final String mediaType;
	
	private ItunesMedia(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String toString() {
		return mediaType;
	}
}
