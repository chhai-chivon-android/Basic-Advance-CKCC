package com.nuon.peter.demoapp.model.detail;

import java.util.List;
import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class Torrents{

	@SerializedName("torrent")
	@Expose
	private List<TorrentItem> torrent;

	public void setTorrent(List<TorrentItem> torrent){
		this.torrent = torrent;
	}

	public List<TorrentItem> getTorrent(){
		return torrent;
	}
}