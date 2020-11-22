<#include "security.ftl">

<#if known>
    <div class="col-3">
        <div class="vertical-nav bg-white" id="sidebar">
            <ul class="nav flex-column bg-white mb-0">
                <li class="nav-item" onclick="switchToCards(this)" id="toCards">
                    <a class="nav-link nav-link ml-3">Карточки</a>
                </li>
                <li class="nav-item" onclick="switchToCards(this)" id="toCoupons">
                    <a class="nav-link nav-link ml-3">Купоны</a>
                </li>
                <li class="nav-item" onclick="switchToCards(this)" id="toLcWallet">
                    <a class="nav-link nav-link ml-3">LC-Кошелек</a>
                </li>
                <li class="nav-item" onclick="switchToCards(this)" id="toEMoney">
                    <a class="nav-link ml-3">Кошелек e-money</a>
                </li>
                <#if isAdmin || isOwner>
                <li class="nav-item" onclick="switchToCards(this)" id="toRoles">
                    <a class="nav-link ml-3">Управление ролями</a>
                </li>
                <li class="nav-item" onclick="switchToCards(this)" id="toUserSettings">
                    <a class="nav-link ml-3">Настройки пользователя</a>
                </li>
                <li class="nav-item" onclick="switchToCards(this)" id="toCompanySettings">
                    <a class="nav-link ml-3">Настройки компании</a>
                </li>
                </#if>
            </ul>
        </div>
    </div>
    <!-- End vertical navbar -->
    <div class="col-9" id="special-info">
        <iframe id="main-frame" src="balance" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>
    </div>
</#if>
