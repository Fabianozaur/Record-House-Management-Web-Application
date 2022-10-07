package recordhouse.Song.domain;

import design.domain.BaseEntity;
import design.exception.ApplicationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Song extends BaseEntity<String> {

    private static Date defaultDate;

    static {
        try {
            defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("1/1/2000");
        } catch (ParseException e) {
            throw new ApplicationException(e);
        }
    }

    private String Title;
    private Date PublishDate;
    private Integer Duration;
    private Integer Likes;

    public String getTitle() {
        return Optional.ofNullable(Title).orElse("");
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getPublishDate() {
        return Optional.ofNullable(PublishDate).orElse(defaultDate);
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public Integer getDuration() {
        return Optional.ofNullable(Duration).orElse(0);
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Integer getLikes() {
        return Optional.ofNullable(Likes).orElse(0);
    }

    public void setLikes(Integer likes) {
        Likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        return Optional.of
                (
                        Optional.of(
                                Optional.ofNullable(o)
                                        .filter(obj -> obj == this)
                                        .isPresent()
                        )
                                .filter(b -> b)
                                .orElse(
                                        Optional.ofNullable(o)
                                                .filter(obj -> obj.getClass() == this.getClass())
                                                .map(obj -> (Song) obj)
                                                .filter(s -> getTitle().equals(s.getTitle()))
                                                .filter(s -> getPublishDate().equals(s.getPublishDate()))
                                                .filter(s -> getDuration().equals(s.getDuration()))
                                                .filter(s -> getLikes().equals(s.getLikes()))
                                                .isPresent()
                                )
                )
                .filter(b -> b)
                .orElse(false);
    }

    @Override
    public int hashCode() {
        int result = Title.hashCode();
        result = 31 * result + PublishDate.hashCode();
        result = 31 * result + Duration;
        result = 31 * result + Likes;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Song { Id = %s, Title = %s, Duration = %d, Likes = %d, PublishDate = %s }",
                super.getId(), Title, Duration, Likes, PublishDate);
    }
}
