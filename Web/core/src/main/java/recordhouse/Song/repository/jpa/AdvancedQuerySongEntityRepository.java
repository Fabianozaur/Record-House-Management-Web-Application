package recordhouse.Song.repository.jpa;

import recordhouse.Song.domain.Song;

import java.util.List;

public interface AdvancedQuerySongEntityRepository {
    List<Song> SearchSongByName(String name);

    Song FindMostLikedSong();

    Song FindLongestSong();
}
