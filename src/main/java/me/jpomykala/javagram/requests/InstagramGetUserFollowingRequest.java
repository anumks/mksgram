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
import me.jpomykala.javagram.ApiConstants;
import me.jpomykala.javagram.requests.payload.InstagramGetUserFollowersResult;

/**
 * Get User Followers Request
 *
 * @author Bruno Candido Volpato da Cunha
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramGetUserFollowingRequest extends InstagramGetRequest<InstagramGetUserFollowersResult> {

  private long userId;
  private String maxId;

  @Override
  public String getUrl() {
    String baseUrl = "friendships/" + userId + "/following/?rank_token=" + api.getRankToken() + "&ig_sig_key_version=" + ApiConstants.API_KEY_VERSION;
    if (maxId != null && !maxId.isEmpty()) {
      baseUrl += "&max_id=" + maxId;
    }
    return baseUrl;
  }

  @Override
  @SneakyThrows
  public InstagramGetUserFollowersResult parseResult(int statusCode, String content) {
    return parseJson(statusCode, content, InstagramGetUserFollowersResult.class);
  }

}
