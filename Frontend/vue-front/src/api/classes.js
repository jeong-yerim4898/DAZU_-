import { instance } from './index';
// 동별 클래스 목록 조회
function fetchClasses(dong) {
  return instance.get(`class/dong/${dong}`);
}
//특정 클래스 데이터 불러오기
function fetchClass(classCode) {
  return instance.get(`class/detail/${classCode}`);
}
// 클래스별 리뷰 리스트 불러오기
function fetchClassReivew(classCode) {
  return instance.get(`class/review/${classCode}`);
}
// 클래스 리뷰 입력
function postReview(reviewData) {
  return instance.post('class/review/insert', reviewData);
}
// 가게 별 모든 클래스 정보 불러오기
function fetchStoreClass(storeCode) {
  return instance.get(`class/store/${storeCode}`);
}
//특정 클래스 질문 목록 조회
function fetchClassQnA(classCode) {
  return instance.get(`class/qna/${classCode}`);
}
// 클래스 질문 등록
function postQnA(qnaData) {
  return instance.post('class/qna/insert', qnaData);
}
// 클래스 질문 답변 등록
function postQnAAnswer(answerData) {
  return instance.post('class/qna/answer/insert', answerData);
}
// 클래스 신청 날짜,시간 정보 가죠오기
function fetchClassTime(classcode) {
  return instance.get(`class/time/${classcode}`);
}
// 클래스 신청(카카오페이 결제준비)
function postKAKAOPAYReady(data) {
  return instance.post('class/kakao/ready', data);
}
// 클래스 신청(카카오페이 결제승인)
function fetchKAKAOPAYAccount(pg_token) {
  return instance.get(`class/kakao/account/${pg_token}`);
}
// 원하는 클래스 시간과 날짜가 포함된 타임코드를 본내다.
function postClassTime(timeinfo) {
  return instance.post('class/insert', timeinfo);
}
export {
  fetchClasses,
  fetchClass,
  fetchStoreClass,
  fetchClassReivew,
  postReview,
  fetchClassQnA,
  postQnA,
  postQnAAnswer,
  fetchClassTime,
  postKAKAOPAYReady,
  fetchKAKAOPAYAccount,
  postClassTime,
};
