/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.core.factory;

import com.github.thierrysquirrel.core.domian.MethodDomain;
import com.github.thierrysquirrel.core.utils.SerializerUtils;
import com.github.thierrysquirrel.repository.entity.OtterEntity;
import com.github.thierrysquirrel.repository.entity.OtterEntityOutput;
import com.github.thierrysquirrel.repository.entity.OtterEntityParameter;

/**
 * ClassName: OtterEntityOutputFactory
 * Description:
 * date: 2020/1/3 19:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class OtterEntityOutputFactory {
	private OtterEntityOutputFactory() {
	}

	public static OtterEntityOutput createOtterEntityOutput(OtterEntity otterEntity) {
		byte[] parameter = otterEntity.getParameter();
		OtterEntityParameter otterEntityParameter = SerializerUtils.deSerialize(parameter, OtterEntityParameter.class);

		MethodDomain methodDomain = OtterContainerFactory.getMethodDomain(otterEntityParameter.getMethodName());
		return new OtterEntityOutput(otterEntity.getId(), methodDomain, otterEntityParameter.getParameter());
	}
}
