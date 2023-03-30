package me.laum.laummeback.frontapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("front")
public class FrontApiController {
    //https://img.shields.io/badge/표시할이름-색상?style=for-the-badge&logo=기술스택아이콘&logoColor=white
    private final FrontApiRepository repository;
    @GetMapping("stack")
    public List<StackEntity> getStacks() {
        List<StackEntity> entities = repository.findAll();
        return entities;
    }

    @GetMapping("stack/add")
    public StackEntity addStack(@RequestParam String name, @RequestParam(required = false) String icon, @RequestParam String hexColor) {
        StackEntity stackEntity = new StackEntity();
        stackEntity.setName(name);
        if (icon != null && !icon.isEmpty()) stackEntity.setIcon(icon);
        stackEntity.setHexColor(hexColor);

        repository.save(stackEntity);
        return stackEntity;
    }

    @GetMapping("stack/remove")
    public StackEntity removeStack(@RequestParam String name) {
        Optional<StackEntity> entity = repository.findByName(name);
        if (entity.isEmpty()) return null;
        StackEntity stack = entity.get();
        repository.deleteById(stack.getId());
        return stack;
    }
}
