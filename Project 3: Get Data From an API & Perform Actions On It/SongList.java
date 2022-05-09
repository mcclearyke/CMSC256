/**
 * Kendall McCleary
 * Project 3
 * Fall CMSC 256
 * This project is searching and sorting song data via the BRIDGES API
 */

package cmsc256;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import bridges.data_src_dependent.Song;
import bridges.connect.Bridges;
import bridges.connect.DataSource;

public class SongList {

	public static void main(String[] args) {

		SongList obj = new SongList(); //Use this to call the methods

		//Calling the checkArgs method to get the command line argument
		String artist = obj.checkArgs(args);

		//Calling the getSongs method
		String songInfo = obj.getSongsByArtist(artist);
			
			System.out.println(songInfo); 
	}

	//Reading an artist name from command line
	public String checkArgs(String[] args) {
		
		//Return value
		String artist = "";

		//If there is command line arguments
		if (args.length > 0) {
			
			//Set artist to what's at the command line
			artist = args[0];

			return artist;	
		}	

		//If there are no command line arguments
		else {

			artist = promptForArtistName(); //Call promptForFileName method
		}

		return artist;
	}

	//If a command line argument is not provided, prompt the user to enter an artist name.
	public String promptForArtistName() {

		//Creating a Scanner
		Scanner in = new Scanner(System.in);

		//Have the console ask the user to enter a file name
		System.out.print("Please enter artist name: ");

			String promptArtistName = in.nextLine(); //Getting the name

		//Returning whatever was entered
		return promptArtistName;
	}

	//Provide method that returns a formatted list of all the songs by that artist -> grouped by album
	public String getSongsByArtist(String artist) throws NullPointerException {
		
		String songName = "";
		String songAlbum = "";
		String songInfo = "";
		
		//Making a bridges object
		Bridges bridges = new Bridges(3, "mcclearyke", "67178589394");
		
		DataSource ds = bridges.getDataSource();
		
		//Create the list of songData
		List <Song> songData = null;

		try {

			songData = ds.getSongData(); //get song data
		} 

		catch (Exception e) {

			System.out.println("Unable to connect to Bridges.");
		}

		//Sorting the songs AND albums in alphabetical order
		Collections.sort(songData, new SongComparator());
			
			//Loop to go through the songData list
			for (Song element : songData) {

				//Getting the songs from the artist entered at the command line
				if (element.getArtist().equals(artist)) {
				
					//Getting the song title
					songName = element.getSongTitle();

					//Getting the album
					songAlbum = element.getAlbumTitle();

					//Formatted string containing all the needed information about the song
					songInfo = songInfo + "Title: " + songName + " Artist: " + artist + " Album: " + songAlbum + "\n";		
				}
			}
			
			//if the artist has no songs in the playlist
			if (songInfo.length() < 1) {
				
				return artist + " has no songs in this playlist";
			}

			//if the artist does have songs on the playlist
			return songInfo; //return the formatted string
	}
}