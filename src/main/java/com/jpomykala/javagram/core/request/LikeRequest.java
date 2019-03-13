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
package com.jpomykala.javagram.core.request;

import com.jpomykala.javagram.core.request.payload.InstagramLikeResult;
import lombok.AllArgsConstructor;
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
public final class LikeRequest extends InstagramPostRequest<InstagramLikeResult>
{

  private long mediaId;

  @Override
  public String getUrl()
  {
    return "media/" + mediaId + "/like/";
  }

  @Override
  @SneakyThrows
  public String getPayload()
  {

    Map<String, Object> likeMap = new LinkedHashMap<>();
    likeMap.put("_uuid", api.getUuid());
    likeMap.put("_uid", api.getUserId());
    likeMap.put("_csrftoken", api.getCsrfCookie());
    likeMap.put("media_id", mediaId);
    return api.getObjectMapper().writeValueAsString(likeMap);
  }

  @Override
  @SneakyThrows
  public InstagramLikeResult parseResult(int statusCode, String content)
  {
    return parseJson(statusCode, content, InstagramLikeResult.class);
  }
}