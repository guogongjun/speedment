/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.core.entity.impl;

import com.speedment.core.entity.BaseEntity;
import com.speedment.core.exception.SpeedmentException;
import com.speedment.core.encoder.JsonEncoder;
import com.speedment.api.Manager;
import com.speedment.core.manager.metaresult.MetaResult;
import com.speedment.core.platform.SpeedmentImpl;
import com.speedment.core.platform.component.ManagerComponent;
import java.util.function.Consumer;

/**
 *
 * @author pemi
 * @param <ENTITY> the entity type
 */
public abstract class AbstractBaseEntity<ENTITY> implements BaseEntity<ENTITY> {

    private final SpeedmentImpl speedment;

    public AbstractBaseEntity(SpeedmentImpl speedment) {
        this.speedment = speedment;
    }

    protected SpeedmentImpl getSpeedment_() {
        return speedment;
    }

    @Override
    public String toJson(JsonEncoder<ENTITY> jsonFormatter) {
        return jsonFormatter.apply((selfAsEntity()));
    }

    @Override
    public String toJson() {
        return manager_().toJson(selfAsEntity());
    }

    @Override
    public ENTITY persist() throws SpeedmentException {
        return manager_().persist(selfAsEntity());
    }

    @Override
    public ENTITY update() throws SpeedmentException {
        return manager_().update(selfAsEntity());
    }

    @Override
    public ENTITY remove() throws SpeedmentException {
        return manager_().remove(selfAsEntity());
    }

    @Override
    public ENTITY persist(Consumer<MetaResult<ENTITY>> consumer) throws SpeedmentException {
        return manager_().persist(selfAsEntity(), consumer);
    }

    @Override
    public ENTITY update(Consumer<MetaResult<ENTITY>> consumer) throws SpeedmentException {
        return manager_().update(selfAsEntity(), consumer);
    }

    @Override
    public ENTITY remove(Consumer<MetaResult<ENTITY>> consumer) throws SpeedmentException {
        return manager_().remove(selfAsEntity(), consumer);
    }

    protected abstract Class<ENTITY> getEntityClass_();

    @SuppressWarnings("unchecked")
    private ENTITY selfAsEntity() {
        return (ENTITY) this;
    }

    protected Manager<ENTITY> manager_() {
        return managerOf_(getEntityClass_());
    }

    protected <T> Manager<T> managerOf_(Class<T> entityClass) {
        return getSpeedment_().get(ManagerComponent.class).managerOf(entityClass);
    }

}
