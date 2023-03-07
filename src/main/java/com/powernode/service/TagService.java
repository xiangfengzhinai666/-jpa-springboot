package com.powernode.service;

import com.powernode.po.Tag;
import com.powernode.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 香风智乃
 * @className TagService
 * @date 2023/2/28 14:11
 * @desciption: 标签的业务层
 * 这个是标签页，还没有书写，先占一个位置，等到最后写完其他的再说，只是可以让其他模块进行调用，最后可能会把这个模块给删除
 */

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTag(Integer size);

    Tag updateTag(Long id,Tag type);

    void deleteTag(Long id);

    List<Tag> listTagTop(Integer size);


}
