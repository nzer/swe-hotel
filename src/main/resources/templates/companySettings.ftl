<#import "parts/common.ftl" as c>

<@c.page>
<div class="container-fluid">

    <div class="row my-3 back">
        <button onclick="window.location.href='main'" class="btn btn-primary"><b>Назад</b></button>
    </div>
    <#if show>
    <#else>
    <div id="company-form">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}"
                               value="<#if company??>${company.name}</#if>" placeholder="Название компании" />
                        <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" name="bin" class="form-control ${(binError??)?string('is-invalid', '')}"
                               value="<#if company??>${company.bin}</#if>" placeholder="БИН" />
                        <#if binError??>
                        <div class="invalid-feedback">
                            ${binError}
                        </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" name="address" class="form-control ${(addressError??)?string('is-invalid', '')}"
                               value="<#if company??>${company.address}</#if>" placeholder="Адрес" />
                        <#if addressError??>
                        <div class="invalid-feedback">
                            ${addressError}
                        </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><b>Сохранить</b></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </#if>

    <#if show>
    <div class="ml-5">
        <div class="row">
            <div class="col-3"><b>Название</b></div>
            <div class="col-6">
                ${company.name}
            </div>
        </div>
        <div class="row">
            <div class="col-3"><b>БИН</b></div>
            <div class="col-6">
                ${company.bin}
            </div>
        </div>
        <div class="row">
            <div class="col-3"><b>Адрес</b></div>
            <div class="col-6">
                ${company.address}
            </div>
        </div>
        <div class="row">
            <div class="col-3"><b>Владелец</b></div>
            <div class="col-6">
                ${company.owner}
            </div>
        </div>
    </div>
    </#if>
</div>
</@c.page>