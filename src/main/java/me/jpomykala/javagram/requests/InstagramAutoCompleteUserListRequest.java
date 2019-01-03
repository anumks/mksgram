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

import lombok.SneakyThrows;
import me.jpomykala.javagram.requests.payload.StatusResult;

/**
 * Autocomplete User List Request
 *
 * @author Bruno Candido Volpato da Cunha
 */
public class InstagramAutoCompleteUserListRequest extends InstagramGetRequest<StatusResult> {

  @Override
  public String getUrl() {
    return "friendships/autocomplete_user_list/";
  }

  @Override
  public String getPayload() {
    return null;
  }

  @Override
  @SneakyThrows
  public StatusResult parseResult(int statusCode, String content) {
    return parseJson(statusCode, content, StatusResult.class);
  }

}
