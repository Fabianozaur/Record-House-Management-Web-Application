package recordhouse.ArtistRecordHouseRelation.service;//package recordhouse.ArtistRecordHouseRelation.service;
//
//import design.domain.Composite;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import recordhouse.Artist.domain.Artist;
//import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
//import recordhouse.RecordHouse.domain.RecordHouse;
//import recordhouse.shared.repository.Repository;
//
//@Service("artistRecordHouseRelationService")
//@Scope("singleton")
//public class ArtistRecordHouseRelationService {
//
//    private final Repository<String, Artist> artistRepository;
//    private final Repository<String, RecordHouse> recordHouseRepository;
//    private final Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository;
//    public ArtistRecordHouseRelationService(
//            @Qualifier("artistXmlRepository") Repository<String, Artist> artistRepository,
//            @Qualifier("recordHouseSerializableRepository") Repository<String, RecordHouse> recordHouseRepository,
//            @Qualifier("artistRecordHouseRelationXmlRepository") Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository){
//        this.artistRepository = artistRepository;
//        this.recordHouseRepository = recordHouseRepository;
//        this.relationRepository = relationRepository;
//    }
//
//    public void AssignArtistToRecordHouse(String artistId, String recordHouseId){
//        artistRepository
//                .findOne(artistId)
//                .orElseThrow(() -> new ArtistRecordHouseRelationError(String.format("Artist with id %s does not exist!", artistId)));
//        recordHouseRepository
//                .findOne(recordHouseId)
//                .orElseThrow(() -> new ArtistRecordHouseRelationError(String.format("Record House with id %s does not exist!", recordHouseId)));
//        ArtistRecordHouseRelation relation = new ArtistRecordHouseRelation();
//        relation.SetArtistId(artistId);
//        relation.SetRecordHouseId(recordHouseId);
//        relationRepository.save(relation);
//    }
//}