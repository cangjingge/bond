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

package com.chungkui.bond.common.core.api;


import java.io.Serializable;

/**
 * 响应信息工具类
 *
 * @param <T>
 * @author lengleng
 */

public class ResBus<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public static <T> ResBus<T> success() {
        return restResult(null, 200, null);
    }

    public static <T> ResBus<T> success(T data) {
        return restResult(data, 200, null);
    }

    public static <T> ResBus<T> success(T data, String msg) {
        return restResult(data, 200, msg);
    }

    public static <T> ResBus<T> failed() {
        return restResult(null, 500, null);
    }

    public static <T> ResBus<T> failed(String msg) {
        return restResult(null, 500, msg);
    }

    public static <T> ResBus<T> failed(T data) {
        return restResult(data, 500, null);
    }

    public static <T> ResBus<T> failed(T data, String msg) {
        return restResult(data, 500, msg);
    }

    public static <T> ResBus<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> ResBus<T> restResult(T data, int code, String msg) {
        ResBus<T> apiResult = new ResBus<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResBus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
