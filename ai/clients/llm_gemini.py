from ai.app.settings import settings
from google import genai

def generate_reply_and_question(user_text: str) -> dict:
    client = genai.Client(api_key=settings.GEMINI_API_KEY)

    prompt = f"""
너는 사용자의 말벗이자 인터뷰어다.
규칙:
- REPLY는 1~2문장, 공감/반응 중심.
- QUESTION은 정확히 1개, 서술형.
- 출력은 두 줄만: REPLY:, QUESTION:

사용자: {user_text}
""".strip()

    # 모델은 최신 라인으로 (문서 기준 예: gemini-2.5-flash)
    resp = client.models.generate_content(
        model="gemini-2.5-flash",
        contents=prompt,
    )

    text = (resp.text or "").strip()

    reply, question = "", ""
    for line in text.splitlines():
        if line.startswith("REPLY:"):
            reply = line.replace("REPLY:", "", 1).strip()
        elif line.startswith("QUESTION:"):
            question = line.replace("QUESTION:", "", 1).strip()

    return {"raw": text, "reply": reply, "question": question}