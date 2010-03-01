package com.trinh;

public class SmugMugAlbum {

	private String title;
	private int id;

	public SmugMugAlbum(int albumId, String albumTitle) {
		this.id = albumId;
		this.title = albumTitle;
	}

	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

}
