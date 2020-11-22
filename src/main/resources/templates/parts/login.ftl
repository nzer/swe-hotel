<#macro login path isRegisterForm>
<form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
    <div class="form-group row">
        <label class="col-6 col-md-4 col-lg-2 col-form-label">User Name :</label>
        <div class="col-6 col-md-4 col-lg-2">
            <input class="form-control ${(usernameError??)?string('is-invalid', '')}" type="text" name="username"
                   value="<#if merchant??>${merchant.username}</#if>" placeholder="User username" />
            <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-6 col-md-4 col-lg-2 col-form-label">Password :</label>
        <div class="col-6 col-md-4 col-lg-2">
            <input class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" name="password" placeholder="Password" />
            <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-6 col-md-4 col-lg-2 col-form-label">Password :</label>
        <div class="col-6 col-md-4 col-lg-2">
            <input class="form-control ${(userError??)?string('is-invalid', '')}" type="password" name="password2" placeholder="Retype password" />
            <#if userError??>
            <div class="invalid-feedback">
                ${userError}
            </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-6 col-md-4 col-lg-2 col-form-label">Email :</label>
        <div class="col-6 col-md-4 col-lg-2">
            <input class="form-control ${(emailError??)?string('is-invalid', '')}" type="email" name="email"
                   value="<#if merchant??>${merchant.email}</#if>" placeholder="@email" />
            <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-6 col-md-4 col-lg-2 col-form-label">Phone number :</label>
        <div class="col-6 col-md-4 col-lg-2">
            <input class="phone form-control ${(numberError??)?string('is-invalid', '')}" type="tel" name="number"
                   value="<#if merchant??>${merchant.number}</#if>"/>
            <#if numberError??>
            <div class="invalid-feedback">
                ${numberError}
            </div>
        </#if>
    </div>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if !isRegisterForm>
    <a href="/registration" class="mr-3">Add new merchant</a>
    </#if>
    <#if isRegisterForm>
    <button class="btn btn-primary mb-2 mr-3" type="submit" />Create</button>
    <a href="/login" class="mr-3">Sign in</a>
    <#else>
    <button class="btn btn-primary mb-2" type="submit" />Sign In</button>
    </#if>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary mb-2" type="submit" />Sign Out</button>
</form>
</#macro>