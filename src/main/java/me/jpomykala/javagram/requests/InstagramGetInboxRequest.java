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

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.jpomykala.javagram.requests.payload.InstagramInboxResult;

/**
 * Inbox Request
 *
 * @author Bruno Candido Volpato da Cunha
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramGetInboxRequest extends InstagramGetRequest<InstagramInboxResult> {

  private String cursor;

  @Override
  public String getUrl() {

    String baseUrl = "direct_v2/inbox/?";
    if (cursor != null && !cursor.isEmpty()) {
      baseUrl += "&cursor=" + cursor;
    }
    return baseUrl;
  }

  @Override
  @SneakyThrows
  public String getPayload() {
    return null;
  }

  @Override
  @SneakyThrows
  public InstagramInboxResult parseResult(int statusCode, String content) {
    return parseJson(statusCode, content, InstagramInboxResult.class);
  }

}
