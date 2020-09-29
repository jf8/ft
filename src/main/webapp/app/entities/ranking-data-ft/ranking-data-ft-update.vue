<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ftApp.rankingData.home.createOrEditLabel" v-text="$t('ftApp.rankingData.home.createOrEditLabel')">Create or edit a RankingDataFt</h2>
                <div>
                    <div class="form-group" v-if="rankingData.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="rankingData.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.name')" for="ranking-data-ft-name">Name</label>
                        <input type="text" class="form-control" name="name" id="ranking-data-ft-name"
                            :class="{'valid': !$v.rankingData.name.$invalid, 'invalid': $v.rankingData.name.$invalid }" v-model="$v.rankingData.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.totalPeople')" for="ranking-data-ft-totalPeople">Total People</label>
                        <input type="number" class="form-control" name="totalPeople" id="ranking-data-ft-totalPeople"
                            :class="{'valid': !$v.rankingData.totalPeople.$invalid, 'invalid': $v.rankingData.totalPeople.$invalid }" v-model.number="$v.rankingData.totalPeople.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.signdPeople')" for="ranking-data-ft-signdPeople">Signd People</label>
                        <input type="number" class="form-control" name="signdPeople" id="ranking-data-ft-signdPeople"
                            :class="{'valid': !$v.rankingData.signdPeople.$invalid, 'invalid': $v.rankingData.signdPeople.$invalid }" v-model.number="$v.rankingData.signdPeople.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.attendance')" for="ranking-data-ft-attendance">Attendance</label>
                        <input type="number" class="form-control" name="attendance" id="ranking-data-ft-attendance"
                            :class="{'valid': !$v.rankingData.attendance.$invalid, 'invalid': $v.rankingData.attendance.$invalid }" v-model.number="$v.rankingData.attendance.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.orderNum')" for="ranking-data-ft-orderNum">Order Num</label>
                        <input type="number" class="form-control" name="orderNum" id="ranking-data-ft-orderNum"
                            :class="{'valid': !$v.rankingData.orderNum.$invalid, 'invalid': $v.rankingData.orderNum.$invalid }" v-model.number="$v.rankingData.orderNum.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.parentId')" for="ranking-data-ft-parentId">Parent Id</label>
                        <input type="number" class="form-control" name="parentId" id="ranking-data-ft-parentId"
                            :class="{'valid': !$v.rankingData.parentId.$invalid, 'invalid': $v.rankingData.parentId.$invalid }" v-model.number="$v.rankingData.parentId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.day')" for="ranking-data-ft-day">Day</label>
                        <div class="d-flex">
                            <input id="ranking-data-ft-day" type="datetime-local" class="form-control" name="day" :class="{'valid': !$v.rankingData.day.$invalid, 'invalid': $v.rankingData.day.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.rankingData.day.$model)"
                            @change="updateInstantField('day', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.isLeaf')" for="ranking-data-ft-isLeaf">Is Leaf</label>
                        <input type="checkbox" class="form-check" name="isLeaf" id="ranking-data-ft-isLeaf"
                            :class="{'valid': !$v.rankingData.isLeaf.$invalid, 'invalid': $v.rankingData.isLeaf.$invalid }" v-model="$v.rankingData.isLeaf.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.rankingData.liveSharing')" for="ranking-data-ft-liveSharing">Live Sharing</label>
                        <select class="form-control" id="ranking-data-ft-liveSharing" name="liveSharing" v-model="rankingData.liveSharing">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="rankingData.liveSharing && liveSharingOption.id === rankingData.liveSharing.id ? rankingData.liveSharing : liveSharingOption" v-for="liveSharingOption in liveSharings" :key="liveSharingOption.id">{{liveSharingOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.rankingData.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./ranking-data-ft-update.component.ts">
</script>
