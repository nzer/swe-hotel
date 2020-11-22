<div id="season-form">
    <div class="form-group">
        <form method="post" enctype="multipart/form-data">
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" name="name" class="form-control" placeholder="Название" />
                </div>
                <div class="form-group">
                    <input type="datetime-local" name="expiration" class="form-control" placeholder="Начало">
                </div>
                <div class="form-group">
                    <input type="datetime-local" name="expiration" class="form-control" placeholder="Конец">
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