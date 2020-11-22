<#import "parts/common.ftl" as c>

<@c.page>
<div class="container-fluid">
    <div class="row my-3 back">
        <button onclick="window.location.href='main'" class="btn btn-primary"><b>Назад</b></button>
    </div>
    <div class="row">
        <div id="name" class="col-2">
            <div class="row">
                <div class="col-12">
                    <p align="center"><i>LC-wallet</i></p>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <p align="center"><b>Название</b></p>
                </div>
            </div>
            <#list cardIssues as cardIssue>
            <div class="row">
                <div class="col-12">
                    <p align="center">${cardIssue.name}</p>
                </div>
            </div>
            </#list>
            <#list couponIssues as couponIssue>
            <div class="row">
                <div class="col-12">
                    <p align="center">${couponIssue.name}</p>
                </div>
            </div>
            </#list>
        </div>

        <div class="table-list">
            <table class="table">
                <thead>
                <tr class="table-names">
                    <th scope="col">LC-wallet</th>
                    <th colspan="2" scope="col">День</th>
                    <th colspan="2" scope="col">Месяц</th>
                </tr>
                <tr class="table-names">
                    <th scope="col">Название</th>
                    <th scope="col">Приход</th>
                    <th scope="col">Расход</th>
                    <th scope="col">Приход</th>
                    <th scope="col">Расход</th>
                </tr>
                </thead>
                <tbody>
                <#list cardIssues as cardIssue>
                <tr>
                    <td>${cardIssue.name}</td>
                    <td>${cardIssue.type}</td>
                    <td>${cardIssue.price}</td>
                    <td>${cardIssue.issued}</td>
                    <td>${cardIssue.bought}</td>
                    <td>${cardIssue.income}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>

    </div>
</div>
</@c.page>