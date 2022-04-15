package com.example.queue.config.api;

import com.example.queue.config.api.bo.Voice;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface VoiceService {

    /**
     * 
     * @param code
     * @param name
     * @param voice
     * @return
     */
    int countVoice(String code, String name, Voice voice);

    /**
     *
     * @param code
     * @param name
     * @param voice
     * @return
     */
    List<Voice> listVoices(String code, String name, Voice voice);

    /**
     *
     * @param id
     * @return
     */
    Voice getVoice(BigInteger id);

    /**
     *
     * @param voice
     * @param creator
     * @return
     */
    Voice insertVoice(Voice voice, String creator);

    /**
     *
     * @param id
     * @param voice
     * @param modifier
     * @return
     */
    Voice updateVoice(BigInteger id, Voice voice, String modifier);

    /**
     *
     * @param id
     * @param modifier
     * @return
     */
    Voice deleteVoice(BigInteger id, String modifier);

}
