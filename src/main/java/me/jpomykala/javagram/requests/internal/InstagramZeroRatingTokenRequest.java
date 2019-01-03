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
package me.jpomykala.javagram.requests.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.jpomykala.javagram.requests.InstagramPostRequest;
import me.jpomykala.javagram.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sync Features Request
 *
 * @author Bruno Candido Volpato da Cunha
 */
@AllArgsConstructor
@Slf4j
public class InstagramZeroRatingTokenRequest extends InstagramPostRequest<StatusResult> {

  @Override
  public String getUrl() {
    return "zr/token/result/";
  }

  @Override
  @SneakyThrows
  public String getPayload() {

    Map<String, Object> likeMap = new LinkedHashMap<>();
    likeMap.put("token_hash", "");

    ObjectMapper mapper = api.getObjectMapper();

    return mapper.writeValueAsString(likeMap);
  }

  @Override
  @SneakyThrows
  public StatusResult parseResult(int statusCode, String content) {
    return new StatusResult();
  }

  /**
   * @return if request must be logged in
   */
  @Override
  public boolean requiresLogin() {
    return false;
  }
}
