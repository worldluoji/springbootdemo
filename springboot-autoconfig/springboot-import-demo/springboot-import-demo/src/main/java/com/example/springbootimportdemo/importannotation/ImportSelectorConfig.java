package com.example.springbootimportdemo.importannotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
* ImportSelector 是一个接口，该接口中只有一个 selectImports 方法，用于返回全类名数组。
 * 所以利用该特性我们可以给容器动态导入 N 个 Bean。
* */

public class ImportSelectorConfig implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.example.springbootimportdemo.importannotation.Pikachu",
                "com.example.springbootimportdemo.importannotation.Charmander"};
    }
}
