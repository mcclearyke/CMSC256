//This is project 4
package cmsc256;

import java.util.Comparator;

import bridges.data_src_dependent.Song;

public class SongComparator implements Comparator <Song> {

public int compare(Song s1, Song s2) throws NullPointerException {
		
		//Check to see if the album title is null
		if (s1.getAlbumTitle() == null) {
			
			s1.setAlbumTitle(""); //set it to a value that can be tested
		}
		
		if (s2.getAlbumTitle() == null) {
			
			s2.setAlbumTitle(""); //set it to a value that can be tested
		}
	
		//Compare the songs
		int songNameCompare = s1.getSongTitle().compareTo(s2.getSongTitle());
		
		//Compare the albums
		int songAlbumCompare = s1.getAlbumTitle().compareTo(s2.getAlbumTitle());

			//If they have the same album
			if (songAlbumCompare == 0) {
				
				//Group the songs together
				return songNameCompare;
			}
	
			else {
		
				return songAlbumCompare;
		}
	}	
}

