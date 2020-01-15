package cn.shuhan.plan.service.impl;

import cn.shuhan.plan.domain.command.*;
import cn.shuhan.plan.domain.dto.ArticleDTO;
import cn.shuhan.plan.domain.dto.PulldownDTO;
import cn.shuhan.plan.domain.dto.TagDTO;
import cn.shuhan.plan.domain.entity.Article;
import cn.shuhan.plan.domain.entity.Tag;
import cn.shuhan.plan.enums.DataStatusEnum;
import cn.shuhan.plan.mapper.ArticleMapper;
import cn.shuhan.plan.mapper.TagMapper;
import cn.shuhan.plan.service.ArticleService;
import cn.shuhan.plan.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengyu
 * @since 2019-11-22
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private final TagMapper tagMapper;

    @Override
    public void create(TagCommand command) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(command, tag);
        tag.setCreateTime(new Date());

        tagMapper.insert(tag);
    }

    @Override
    public TagDTO detail(Long id) {
        Tag tag = tagMapper.selectById(id);
        TagDTO dto = new TagDTO();
        BeanUtils.copyProperties(tag, dto);

        return dto;
    }

    @Override
    public IPage<Tag> listTag(TagQueryCommand command) {
        Page page = PageCommand.builder(command);
        String[] descs = page.descs();
        if (null != descs) {
            List<String> list = Arrays.asList(descs);
            List<String> list1 = new ArrayList<String>(list);
            list1.add("status");
            page.setDescs(list1);
        }else {
            page.setDesc("status");
        }
        LambdaQueryWrapper<Tag> wrapper = new QueryWrapper<Tag>().lambda()
                .eq(Tag::getUserId, command.getUserId())
                .eq(Tag::getDataStatus, DataStatusEnum.NORMAL.code);
        return tagMapper.selectPage(page,wrapper);

    }

    @Override
    public void updateTag(TagCommand command) {
        tagMapper.updateByCommand(command);
    }

    @Override
    public void changeStatus(Long id, Integer status, Integer dataStatus) {
        tagMapper.ChangStatus(id, status,dataStatus);
    }

    @Override
    public List<PulldownDTO> pulldownTags() {
        List<PulldownDTO> dto = tagMapper.listPulldown();
        return dto;
    }
}
