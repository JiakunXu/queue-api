package com.example.queue.media.api;

import com.example.queue.media.api.bo.Media;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface MediaService {

    /**
     *
     * @param code
     * @param name
     * @param media
     * @return
     */
    int countMedia(String code, String name, Media media);

    /**
     * 
     * @param code
     * @param name
     * @param media
     * @return
     */
    List<Media> listMedias(String code, String name, Media media);

    /**
     * 
     * @param id
     * @return
     */
    Media getMedia(BigInteger id);

    /**
     *
     * @param media
     * @param creator
     * @return
     */
    Media insertMedia(Media media, String creator);

    /**
     *
     * @param id
     * @param media
     * @param modifier
     * @return
     */
    Media updateMedia(BigInteger id, Media media, String modifier);

    /**
     *
     * @param id
     * @param modifier
     * @return
     */
    Media deleteMedia(BigInteger id, String modifier);

}
