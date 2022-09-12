<template>
  <transition name="modal" appear>
		<div class="popup-box  animated fadeIn fast02">
			<!--개인 정보-->
			<div class="popbox-set">
				<form>
					<div class="popup-text">
						<p>로그아웃을 하실경우</p>
						<p>현재 정보는 저장되지 않습니다.</p>
							<p><i class="fa"></i></p>
						<p>로그아웃 하시겠습니까?</p>
					</div>
					<div class="btn-box">
						<button type="button" class="btn btn-b btn-s">로그아웃</button>
						<button type="button" class="btn btn-n btn-s btn_close" @click="$emit('close')">닫기</button>
					</div>
				</form>
			</div>
		</div>
  </transition>
</template>