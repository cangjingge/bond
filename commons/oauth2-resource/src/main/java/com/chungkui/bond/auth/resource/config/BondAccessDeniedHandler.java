/*
 *
 *  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.chungkui.bond.auth.resource.config;

/**
 * @author lengleng
 * @date 2019/2/1
 */

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lengleng 授权拒绝处理器，覆盖默认的OAuth2AccessDeniedHandler 包装失败信息到PigDeniedException
 */
@Component
public class BondAccessDeniedHandler extends OAuth2AccessDeniedHandler {

	/**
	 * 授权拒绝处理，使用R包装
	 * @param request request
	 * @param response response
	 * @param authException authException
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("CommonConstants.CONTENT_TYPE");
		ResBus<Exception> result = ResBus.failed(new Exception("授权失败，禁止访问"));
		response.setStatus(403);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(JSONObject.toJSONString(result));
	}

}
