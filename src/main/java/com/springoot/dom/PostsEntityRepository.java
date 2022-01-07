package com.springoot.dom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsEntityRepository extends CrudRepository<PostsEntity, Long> {

    PostsEntity findById(long id);
}