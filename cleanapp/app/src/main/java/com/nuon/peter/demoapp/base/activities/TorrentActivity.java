package com.nuon.peter.demoapp.base.activities;

import com.nuon.peter.demoapp.base.component.torrent.TorrentService;

public interface TorrentActivity {

	TorrentService getTorrentService();

	void onTorrentServiceConnected();

	void onTorrentServiceDisconnected();
}
