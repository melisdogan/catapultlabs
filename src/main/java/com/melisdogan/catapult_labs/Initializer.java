package com.melisdogan.catapult_labs;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer { // <1>

    public Initializer() {
        super(CatapultLabsApplication.HttpSessionConfig.class); // <2>
    }

}
