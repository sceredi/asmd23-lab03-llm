# Testing llm's code generation capabilities

## Task 1

### ChatGPT
- Model: GPT 3.5
- Chat link: [testing chatgpt](https://chat.openai.com/share/77f4c146-886d-43cf-b9b9-127409313931)

I had to instruct it in order for it not to spit out some "random" code immediately and ask it nicely to wait for all the
interfaces definitions and it took quite a few tries in order to get it right. At some point it didn't want to work 
anymore and asked me to implement stuff, but simply asking it to do it for me was enough to maintain my lazyness
intact. After a few errors I had to ask if it wanted to see the test definition again and doing so helped quite a bit
with the following answers.

- Probably using GPT 4 would have helped
- Happy that I am faster than GPT 3.5 at not so trivial implementations

### Copilot
I am not a big fan of letting copilot free, I tend to use it only for boilerplate, so my current setup is probably not 
optimized to allow copilot to implement a good portion of the code.\
I tried opening the project using vscode as as suggested during class it was the editor with the best integration,
probably because I haven't set it up properly copilot wasn't able to access the project files, making it not very 
usefull when it comes to code generation.\
I then switched back to neovim where I used it to help me writing the code. I tried to let his "immagination" run free
at the beginning and tried to intervene only modifing the code it created but quickly noticed that the solution it was 
creating were sub optimal at best. I then used it similarly to the way I always use it, helping me with boilerplate code.

- Don't think it is ready to write the code for me
- Has a lot of sub-optimal solutions
I think this is to be expected, since a lot of the code that it was trained on was of questionable quality.
