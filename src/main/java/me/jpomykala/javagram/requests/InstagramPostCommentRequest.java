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
package me.jpomykala.javagram.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.jpomykala.javagram.requests.payload.InstagramPostCommentResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Comment Post Request
 *
 * @author Bruno Candido Volpato da Cunha
 */
@AllArgsConstructor
@Slf4j
public class InstagramPostCommentRequest extends InstagramPostRequest<InstagramPostCommentResult> {

  private long mediaId;
  private String commentText;

  @Override
  public String getUrl() {
    return "media/" + mediaId + "/comment/";
  }

  @Override
  @SneakyThrows
  public String getPayload() {

    Map<String, Object> likeMap = new LinkedHashMap<>();
    likeMap.put("_uuid", api.getUuid());
    likeMap.put("_uid", api.getUserId());
    likeMap.put("_csrftoken", api.getOrFetchCsrf());
    likeMap.put("comment_text", commentText);

    ObjectMapper mapper = api.getObjectMapper();
    String payloadJson = mapper.writeValueAsString(likeMap);

    return payloadJson;
  }

  @Override
  @SneakyThrows
  public InstagramPostCommentResult parseResult(int statusCode, String content) {
    return parseJson(statusCode, content, InstagramPostCommentResult.class);
  }
}
