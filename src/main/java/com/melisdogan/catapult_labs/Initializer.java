package com.melisdogan.catapult_labs;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer {

    public Initializer() {
        super(CatapultLabsApplication.HttpSessionConfig.class);
    }

}
