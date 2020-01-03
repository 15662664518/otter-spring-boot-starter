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

package com.github.thierrysquirrel.interceptor.dubbo;

import com.github.thierrysquirrel.core.constant.InterceptorConstant;
import com.github.thierrysquirrel.core.utils.GlobalIdUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.Optional;

/**
 * ClassName: OtterDubboConsumerFilter
 * Description:
 * date: 2020/1/3 16:08
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Activate(group = CommonConstants.CONSUMER)
public class OtterDubboConsumerFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) {
		Optional<Long> globalId = Optional.ofNullable(GlobalIdUtils.getId());
		globalId.ifPresent(id -> RpcContext.getContext().setAttachment(InterceptorConstant.INTERCEPTOR_IDENTIFIER.getValue(), String.valueOf(id)));
		return invoker.invoke(invocation);
	}
}
