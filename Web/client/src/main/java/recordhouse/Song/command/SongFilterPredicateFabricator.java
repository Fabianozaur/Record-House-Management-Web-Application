package recordhouse.Song.command;

import org.springframework.stereotype.Service;
import service.Song.SongViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class SongFilterPredicateFabricator {

    public static final String attributesRegex = "(duration|likes|published)";
    public static final String conditionsRegex = "[><=]";
    public static final String criteriaNumberRegex = "[0-9]+";
    public static final String criteriaDateRegex = "[0-9]{1,2}/[0-9]{1,2}/[1-9][0-9]{0,3}";
    public static final String criteriaRegex = String.format("(%s)|(%s)", criteriaNumberRegex, criteriaDateRegex);

    public void ValidateInput(String attribute, String condition, String criteria){
        Optional.of(Pattern.matches(attributesRegex, attribute))
                .filter(b -> b)
                .orElseThrow(() -> new SongCommandException(String.format("%s is invalid attribute to filter songs by.", attribute)));
        Optional.of(Pattern.matches(conditionsRegex, condition))
                .filter(b -> b)
                .orElseThrow(() -> new SongCommandException(String.format("%s is invalid condition to filter songs by.", condition)));
        Optional.of(condition.equals("published"))
                .filter(b -> b && Optional.of(Pattern.matches(criteriaDateRegex, criteria))
                .orElseThrow(() -> new SongCommandException("If attribute is date, condition must be a date.")));
        Optional.of(!condition.equals("published"))
                .filter(b -> b && Optional.of(Pattern.matches(criteriaNumberRegex, criteria))
                        .orElse(false))
                .orElseThrow(() -> new SongCommandException("Criteria must be a number!"));
    }

    public Predicate<SongViewModel> FabricatePredicate(String attribute, String condition, String criteria){
        switch(attribute){
            case "published" -> {
                final Date publishDate;
                try {
                    publishDate = new SimpleDateFormat("dd/mm/yyyy").parse(criteria);
                } catch (ParseException e) {
                    throw new SongCommandException(String.format("Error in filter song command, unable to read date: %s", e.getMessage()));
                }
                return FabricatePredicateForPublishDate(condition, publishDate);
            }
            default -> {
                final int number;
                try {
                    number = Integer.parseInt(criteria);
                } catch (NumberFormatException e) {
                    throw new SongCommandException(String.format("Error in filter song command, unable to read number: %s", e.getMessage()));
                }
                return FabricatePredicateForAttribute(attribute, condition, number);
            }
        }
    }

    public Predicate<SongViewModel> FabricatePredicateForPublishDate(String condition, Date publishDate){
        return switch (condition) {
            case "<" -> (s) -> 0 < s.PublishDate.compareTo(publishDate);
            case "=" -> (s) -> s.PublishDate == publishDate;
            case ">" -> (s) -> 0 > s.PublishDate.compareTo(publishDate);
            default -> throw new SongCommandException("Invalid condition");
        };
    }

    public Predicate<SongViewModel> FabricatePredicateForAttribute(String attribute, String condition, int number){
        return switch (String.format("%s-%s", attribute, condition)) {
            case "duration-<" -> (s) -> s.Duration < number;
            case "duration-=" -> (s) -> s.Duration == number;
            case "duration->" -> (s) -> s.Duration > number;
            case "likes-<" -> (s) -> s.Likes < number;
            case "likes-=" -> (s) -> s.Likes == number;
            case "likes->" -> (s) -> s.Likes > number;
            default -> throw new SongCommandException("Invalid attribute & condition combination");
        };
    }
}
