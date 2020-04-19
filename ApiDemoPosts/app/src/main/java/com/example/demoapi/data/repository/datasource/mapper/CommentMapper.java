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


import com.example.demoapi.data.entites.CommentEntity;
import com.example.demoapi.mvp.model.Comment;

public class CommentMapper extends Mapper<Comment, CommentEntity> {
    @Override
    public CommentEntity map(Comment value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Comment reverseMap(CommentEntity value) {
        Comment comment = new Comment();

        comment.setBody(value.getBody());
        comment.setEmail(value.getEmail());
        comment.setId(value.getId());
        comment.setName(value.getName());
        comment.setPostId(value.getPostId());
        return comment;
    }


}

