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
package me.jpomykala.javagram.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Friendship Status VO
 *
 * @author Bruno Candido Volpato da Cunha
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramFriendshipStatus {
  public boolean following;
  public boolean followed_by;
  public boolean incoming_request;
  public boolean outgoing_request;
  public boolean is_private;
  public boolean is_blocking_reel;
  public boolean is_muting_reel;
  public boolean blocking;
  public boolean is_bestie;

}
