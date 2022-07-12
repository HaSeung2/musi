package dto;

import java.util.Date;

public class MusicDTO {
public String songname;
public String sing;
public String songlyrics;
public String songlyricist;
public String songcomposer;
public Date songtime;
public String musicianid;
int songlickcnt;
public MusicDTO(String songname, String sing, String songlyrics, String songlyricist, String songcomposer,String musicianid) {
	this.songname = songname;
	this.sing = sing;
	this.songlyrics = songlyrics;
	this.songlyricist = songlyricist;
	this.songcomposer = songcomposer;
	this.songtime = null;
	this.musicianid = musicianid;
	this.songlickcnt = 0;
}
}
