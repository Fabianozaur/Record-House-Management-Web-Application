package rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;
import rest.service.*;
import service.Artist.ArtistService;
import service.ArtistRecordHouse.ArtistRecordHouseRelationService;
import service.Playlist.PlaylistService;
import service.PlaylistSong.PlaylistSongRelationService;
import service.Song.SongService;
import service.SongArtist.SongArtistRelationService;

@Configuration
@PropertySources({@PropertySource(value = "classpath:network.properties"),
})
public class SpringRESTConfiguration {

    @Value("${server.protocol}")
    private String SERVER_PROTOCOL;

    @Value("${server.host}")
    private String SERVER_HOST;

    @Value("${server.port}")
    private String SERVER_PORT;

    @Value("${server.endpoint}")
    private String SERVER_ENDPOINT;

    @Bean("remoteUrl")
    protected String GetRemoteUrl(){
        return String.format("%s://%s:%s%s",
                SERVER_PROTOCOL,
                SERVER_HOST,
                SERVER_PORT,
                SERVER_ENDPOINT
        );
    }

    @Bean
    public RestTemplate GetRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public SongService GetProxySongService(RESTProxyProvider proxyProvider){
        return proxyProvider.GetProxy(SongService.class, RESTSongService.class);
    }

    @Bean
    public SongArtistRelationService GetProxySongArtistRElationService(RESTProxyProvider proxyProvider){
        return proxyProvider.GetProxy(SongArtistRelationService.class, RESTSongArtistRelationService.class);
    }
    @Bean
    public PlaylistService GetProxyPlaylistService(RESTProxyProvider proxyProvider){
        return proxyProvider.GetProxy(PlaylistService.class, RESTPlaylistService.class);
    }

    @Bean
    public PlaylistSongRelationService GetProxyPlaylistSongRElationService(RESTProxyProvider proxyProvider){
        return proxyProvider.GetProxy(PlaylistSongRelationService.class, RESTPlaylistSongRelationService.class);
    }
    @Bean
    public ArtistService GetProxyArtistService(RESTProxyProvider proxyProvider){
        return proxyProvider.GetProxy(ArtistService.class, RESTArtistService.class);
    }

    @Bean
    public ArtistRecordHouseRelationService GetProxyArtistRecordHouseRElationService(RESTProxyProvider proxyProvider){
        return proxyProvider.GetProxy(ArtistRecordHouseRelationService.class, RESTArtistRecordHouseRelationService.class);
    }
}
