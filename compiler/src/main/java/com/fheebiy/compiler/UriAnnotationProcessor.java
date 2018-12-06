package com.fheebiy.compiler;

import com.fheebiy.interfaces.RouterUri;
import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created on 2018/12/6.
 *
 * @author bob zhou.
 * Description:
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class UriAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        log("UriAnnotationProcessor process!!!");
        if (set == null || set.isEmpty()) {
            return false;
        }
        log("UriAnnotationProcessor process!!!");
        for (Element element : roundEnvironment.getElementsAnnotatedWith(RouterUri.class)) {
            RouterUri actUri = element.getAnnotation(RouterUri.class);
            String[] paths = actUri.path();
            String scheme = actUri.scheme();
            String host = actUri.host();
            log("paths = " + paths[0] + " ,scheme = " + scheme + " ,host = " + host);
        }
        return false;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        /**
         * 这个地方需要格外注意，必须重写这个方法，不然会一直报错：
         * No SupportedAnnotationTypes annotation
         */
        return Collections.singleton(RouterUri.class.getCanonicalName());
    }

    private void log(String msg) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }
}
