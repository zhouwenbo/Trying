package com.fheebiy.oplugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created on 2018/11/27.
 *
 * @author bob zhou.
 * Description:
 */
public class OPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        final OPluginExtension extension = project.getExtensions().create(Constants.NAME, OPluginExtension.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println("extension.getName:" + extension.getName());
                System.out.println("extension.getVersion:" + extension.getVersion());
                System.out.println("extension.isOpen:" + extension.getIsOpen());
            }
        });
    }
}
