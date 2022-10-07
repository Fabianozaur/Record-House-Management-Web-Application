package service.Song;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import shared.converter.Song.SongViewModelDeserializer;
import shared.converter.Song.SongViewModelSerializer;

import java.io.Serializable;
import java.util.Date;

@JsonSerialize(using = SongViewModelSerializer.class)
@JsonDeserialize(using = SongViewModelDeserializer.class)
public class SongViewModel implements Serializable {
    public String Id;
    public String Title;
    public Date PublishDate;
    public int Likes;
    public int Duration;
}
