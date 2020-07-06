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


import com.alibaba.fastjson.JSONObject;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json; charset=utf-8");
        ResBus<String> result = new ResBus<>();
        result.setCode(401);
        if (authException != null) {
            result.setMsg("error");
            result.setData(authException.getMessage());
        }
        response.setStatus(401);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONObject.toJSONString(request));
    }

}
