import tkinter as tk
from tkinter import ttk
from tkinter import Canvas

window = tk.Tk()
window.title("Prototype 2")
window.geometry("800x600")
#window.resizable(0, 0)

mainframe = ttk.Frame(window)
mainframe.grid(column=0, row=0)

mainframe.columnconfigure(1, weight=1)
mainframe.rowconfigure(1, weight=1)

mainframe.columnconfigure((0, 1, 2, 3, 4), pad=30)
mainframe.rowconfigure((0, 1, 2, 3), pad=30)

mainframe.pack(fill=tk.BOTH, expand=True, padx=5, pady= 5)

openButton = ttk.Button(mainframe, text="Open").grid(row = 0, column = 0, sticky='NWES')

graph = Canvas(mainframe, borderwidth = '1', relief='solid').grid(row = 0, column = 1, columnspan=3, rowspan=2, sticky='NWES', padx=(10, 0))

clearButton = ttk.Button(mainframe, text="Clear").grid(row = 2, column = 1, columnspan=3, sticky='NS')

saveButton = ttk.Button(mainframe, text="Save").grid(row = 3, column = 0, sticky='NWES')

exitButton = ttk.Button(mainframe, text="Exit").grid(row = 3, column = 3, rowspan=2, sticky='NWES')

window.mainloop()