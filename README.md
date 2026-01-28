# Doran  
> Voice-first Autobiography & Memory Recording Service

---

## 📌 Project Overview

**Doran**은 음성 기반 대화를 통해 사용자의 기억을 기록하고,  
이를 에피소드 단위로 정리하여 **하나의 자서전으로 완성하는 AI 서비스**입니다.

텍스트 중심 디지털 환경에 익숙하지 않은 사용자도  
자연스러운 대화를 통해 자신의 삶을 남길 수 있도록 설계되었습니다.

---

## 🎯 Problem Statement

- 많은 사람들이 하루를 대화 없이 보내며 정서적 고립을 경험함
- 기존 AI는 대화를 기억하지 못해 관계 형성이 어려움
- 텍스트 중심 UI는 고령층 및 비숙련 사용자에게 높은 진입장벽이 됨

---

## 💡 Solution

Doran은 단순한 Q&A AI가 아닌, **말벗이자 기록 파트너**를 지향합니다.

- **Long-term Memory**  
  이전 대화를 기억하여 오늘의 대화가 내일로 이어지는 경험 제공

- **Proactive Interviewer**  
  사용자가 먼저 묻지 않아도 AI가 회상 기반 질문을 제시

- **Voice-first UX**  
  버튼 한 번으로 시작하는 100% 음성 중심 인터랙션

---

## 🔁 User Flow

1. 온보딩 (호칭, 말투, 기본 정보 설정)
2. 인터뷰룸에서 음성 대화 진행
3. 대화 로그 기반 에피소드 생성
4. 에피소드 선택
5. 자서전 생성
6. 서재에서 자서전 열람 및 관리

---

## 🖥️ Screen Walkthrough

### 1. Onboarding
- 최초 1회 진행
- 사용자 맞춤 대화를 위한 기본 정보 설정

### 2. Main Page
- 오늘의 질문 제시
- 인터뷰룸 바로 진입
- 최근 에피소드 및 자서전 요약 확인

### 3. Interview Room
- AI 주도 질문 + 음성 응답
- 대화 상태 시각적 피드백 제공
- 대화 종료 시 자동 기록

### 4. Make Episodes
- 대화 로그를 에피소드 단위로 정리
- 자서전에 포함할 에피소드 선택

### 5. Library & Autobiography
- 자서전 형태로 기록 축적
- 열람 / 수정 / 공유 가능

---

## 🧠 Tech Highlights

- Speech-to-Text (STT)
- 대화 요약 및 에피소드 분리
- 자서전 생성 (서사 구조화)
- 사용자 맥락 기반 메모리 관리

---

## 🏗️ Repository Structure

```text
root/
├── frontend/      # Client
├── backend/       # Server API
├── ai/            # AI Pipeline
├── README.md
└── .gitignore
