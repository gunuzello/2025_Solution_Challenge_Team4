from ai.clients.llm_gemini import generate_reply_and_question

if __name__ == "__main__":
    out = generate_reply_and_question("오늘 좀 기분이 묘해. 예전 생각이 나.")
    print(out["raw"])
    print("---")
    print("reply:", out["reply"])
    print("question:", out["question"])