/*
 * Copyright (C) 2016 Erik Jhordan Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demoapi.data.repository.datasource.mapper;


import com.example.demoapi.data.entites.PostEntity;
import com.example.demoapi.mvp.model.Post;

public class PostMapper extends Mapper<Post, PostEntity> {




    @Override
    public PostEntity map(Post value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Post reverseMap(PostEntity postEntity) {
        Post post = new Post();
        post.setId(postEntity.getId());
        post.setBody(postEntity.getBody());
        post.setTitle(postEntity.getTitle());
        post.setUserId(postEntity.getUserId());
        return post;
    }


}

