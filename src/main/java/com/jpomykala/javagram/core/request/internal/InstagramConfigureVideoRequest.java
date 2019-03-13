/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jpomykala.javagram.core.request.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpomykala.javagram.bot.ApiConstants;
import com.jpomykala.javagram.core.request.InstagramPostRequest;
import com.jpomykala.javagram.core.request.payload.StatusResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Like Request
 *
 * @author Bruno Candido Volpato da Cunha
 */
@AllArgsConstructor
@Slf4j
@Builder
public class InstagramConfigureVideoRequest extends InstagramPostRequest<StatusResult>
{

  private String uploadId;
  private String caption;

  private long duration;
  private int width;
  private int height;

  @Override
  public String getUrl()
  {
    return "media/configure/?video=1";
  }

  @Override
  @SneakyThrows
  public String getPayload()
  {

    Map<String, Object> likeMap = new LinkedHashMap<>();
    likeMap.put("upload_id", uploadId);
    likeMap.put("source_type", 3);
    likeMap.put("poster_frame_index", 0);
    likeMap.put("length", 0.0);
    likeMap.put("audio_muted", false);
    likeMap.put("filter_type", 0);
    likeMap.put("video_result", "deprecated");

    Map<String, Object> clips = new LinkedHashMap<>();
    clips.put("length", duration);
    clips.put("source_type", 3);
    clips.put("camera_position", "back");
    likeMap.put("clips", "clips");

    Map<String, Object> extraMap = new LinkedHashMap<>();
    extraMap.put("source_width", width);
    extraMap.put("source_height", height);
    likeMap.put("extra", extraMap);

    Map<String, Object> deviceMap = new LinkedHashMap<>();
    deviceMap.put("manufacturer", ApiConstants.DEVICE_MANUFACTURER);
    deviceMap.put("model", ApiConstants.DEVICE_MODEL);
    deviceMap.put("android_version", ApiConstants.DEVICE_ANDROID_VERSION);
    deviceMap.put("android_release", ApiConstants.DEVICE_ANDROID_RELEASE);
    likeMap.put("device", deviceMap);

    likeMap.put("_csrftoken", api.getCsrfCookie());
    likeMap.put("_uuid", api.getUuid());
    likeMap.put("_uid", api.getUserId());
    likeMap.put("caption", caption);

    ObjectMapper mapper = api.getObjectMapper();
    String payloadJson = mapper.writeValueAsString(likeMap);

    return payloadJson;
  }

  @Override
  @SneakyThrows
  public StatusResult parseResult(int statusCode, String content)
  {
    return parseJson(statusCode, content, StatusResult.class);
  }

}
